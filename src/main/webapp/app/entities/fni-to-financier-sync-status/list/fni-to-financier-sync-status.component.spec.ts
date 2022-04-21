import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { FniTOFinancierSyncStatusService } from '../service/fni-to-financier-sync-status.service';

import { FniTOFinancierSyncStatusComponent } from './fni-to-financier-sync-status.component';

describe('FniTOFinancierSyncStatus Management Component', () => {
  let comp: FniTOFinancierSyncStatusComponent;
  let fixture: ComponentFixture<FniTOFinancierSyncStatusComponent>;
  let service: FniTOFinancierSyncStatusService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [FniTOFinancierSyncStatusComponent],
    })
      .overrideTemplate(FniTOFinancierSyncStatusComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FniTOFinancierSyncStatusComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(FniTOFinancierSyncStatusService);

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
    expect(comp.fniTOFinancierSyncStatuses?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
