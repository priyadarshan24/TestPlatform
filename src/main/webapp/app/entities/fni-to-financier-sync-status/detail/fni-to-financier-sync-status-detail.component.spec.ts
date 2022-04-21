import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FniTOFinancierSyncStatusDetailComponent } from './fni-to-financier-sync-status-detail.component';

describe('FniTOFinancierSyncStatus Management Detail Component', () => {
  let comp: FniTOFinancierSyncStatusDetailComponent;
  let fixture: ComponentFixture<FniTOFinancierSyncStatusDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FniTOFinancierSyncStatusDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ fniTOFinancierSyncStatus: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(FniTOFinancierSyncStatusDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(FniTOFinancierSyncStatusDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load fniTOFinancierSyncStatus on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.fniTOFinancierSyncStatus).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
