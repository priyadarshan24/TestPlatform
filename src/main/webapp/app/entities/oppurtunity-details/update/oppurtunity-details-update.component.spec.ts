import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { OppurtunityDetailsService } from '../service/oppurtunity-details.service';
import { IOppurtunityDetails, OppurtunityDetails } from '../oppurtunity-details.model';
import { IFniTOFinancierSyncStatus } from 'app/entities/fni-to-financier-sync-status/fni-to-financier-sync-status.model';
import { FniTOFinancierSyncStatusService } from 'app/entities/fni-to-financier-sync-status/service/fni-to-financier-sync-status.service';

import { OppurtunityDetailsUpdateComponent } from './oppurtunity-details-update.component';

describe('OppurtunityDetails Management Update Component', () => {
  let comp: OppurtunityDetailsUpdateComponent;
  let fixture: ComponentFixture<OppurtunityDetailsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let oppurtunityDetailsService: OppurtunityDetailsService;
  let fniTOFinancierSyncStatusService: FniTOFinancierSyncStatusService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [OppurtunityDetailsUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(OppurtunityDetailsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OppurtunityDetailsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    oppurtunityDetailsService = TestBed.inject(OppurtunityDetailsService);
    fniTOFinancierSyncStatusService = TestBed.inject(FniTOFinancierSyncStatusService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call financierSyncStatus query and add missing value', () => {
      const oppurtunityDetails: IOppurtunityDetails = { id: 456 };
      const financierSyncStatus: IFniTOFinancierSyncStatus = { id: 69805 };
      oppurtunityDetails.financierSyncStatus = financierSyncStatus;

      const financierSyncStatusCollection: IFniTOFinancierSyncStatus[] = [{ id: 9305 }];
      jest.spyOn(fniTOFinancierSyncStatusService, 'query').mockReturnValue(of(new HttpResponse({ body: financierSyncStatusCollection })));
      const expectedCollection: IFniTOFinancierSyncStatus[] = [financierSyncStatus, ...financierSyncStatusCollection];
      jest.spyOn(fniTOFinancierSyncStatusService, 'addFniTOFinancierSyncStatusToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ oppurtunityDetails });
      comp.ngOnInit();

      expect(fniTOFinancierSyncStatusService.query).toHaveBeenCalled();
      expect(fniTOFinancierSyncStatusService.addFniTOFinancierSyncStatusToCollectionIfMissing).toHaveBeenCalledWith(
        financierSyncStatusCollection,
        financierSyncStatus
      );
      expect(comp.financierSyncStatusesCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const oppurtunityDetails: IOppurtunityDetails = { id: 456 };
      const financierSyncStatus: IFniTOFinancierSyncStatus = { id: 64225 };
      oppurtunityDetails.financierSyncStatus = financierSyncStatus;

      activatedRoute.data = of({ oppurtunityDetails });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(oppurtunityDetails));
      expect(comp.financierSyncStatusesCollection).toContain(financierSyncStatus);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<OppurtunityDetails>>();
      const oppurtunityDetails = { id: 123 };
      jest.spyOn(oppurtunityDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ oppurtunityDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: oppurtunityDetails }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(oppurtunityDetailsService.update).toHaveBeenCalledWith(oppurtunityDetails);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<OppurtunityDetails>>();
      const oppurtunityDetails = new OppurtunityDetails();
      jest.spyOn(oppurtunityDetailsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ oppurtunityDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: oppurtunityDetails }));
      saveSubject.complete();

      // THEN
      expect(oppurtunityDetailsService.create).toHaveBeenCalledWith(oppurtunityDetails);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<OppurtunityDetails>>();
      const oppurtunityDetails = { id: 123 };
      jest.spyOn(oppurtunityDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ oppurtunityDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(oppurtunityDetailsService.update).toHaveBeenCalledWith(oppurtunityDetails);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackFniTOFinancierSyncStatusById', () => {
      it('Should return tracked FniTOFinancierSyncStatus primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackFniTOFinancierSyncStatusById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
