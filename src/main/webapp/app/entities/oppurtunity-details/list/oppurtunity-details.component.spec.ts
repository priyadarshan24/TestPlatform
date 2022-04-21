import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { OppurtunityDetailsService } from '../service/oppurtunity-details.service';

import { OppurtunityDetailsComponent } from './oppurtunity-details.component';

describe('OppurtunityDetails Management Component', () => {
  let comp: OppurtunityDetailsComponent;
  let fixture: ComponentFixture<OppurtunityDetailsComponent>;
  let service: OppurtunityDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [OppurtunityDetailsComponent],
    })
      .overrideTemplate(OppurtunityDetailsComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OppurtunityDetailsComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OppurtunityDetailsService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.oppurtunityDetails?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
