import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OppurtunityDetailsDetailComponent } from './oppurtunity-details-detail.component';

describe('OppurtunityDetails Management Detail Component', () => {
  let comp: OppurtunityDetailsDetailComponent;
  let fixture: ComponentFixture<OppurtunityDetailsDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OppurtunityDetailsDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ oppurtunityDetails: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(OppurtunityDetailsDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OppurtunityDetailsDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load oppurtunityDetails on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.oppurtunityDetails).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
