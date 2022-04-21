import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { FniTOFinancierSyncStatusService } from '../service/fni-to-financier-sync-status.service';
import { IFniTOFinancierSyncStatus, FniTOFinancierSyncStatus } from '../fni-to-financier-sync-status.model';

import { FniTOFinancierSyncStatusUpdateComponent } from './fni-to-financier-sync-status-update.component';

describe('FniTOFinancierSyncStatus Management Update Component', () => {
  let comp: FniTOFinancierSyncStatusUpdateComponent;
  let fixture: ComponentFixture<FniTOFinancierSyncStatusUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let fniTOFinancierSyncStatusService: FniTOFinancierSyncStatusService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [FniTOFinancierSyncStatusUpdateComponent],
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
      .overrideTemplate(FniTOFinancierSyncStatusUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FniTOFinancierSyncStatusUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    fniTOFinancierSyncStatusService = TestBed.inject(FniTOFinancierSyncStatusService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const fniTOFinancierSyncStatus: IFniTOFinancierSyncStatus = { id: 456 };

      activatedRoute.data = of({ fniTOFinancierSyncStatus });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(fniTOFinancierSyncStatus));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<FniTOFinancierSyncStatus>>();
      const fniTOFinancierSyncStatus = { id: 123 };
      jest.spyOn(fniTOFinancierSyncStatusService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fniTOFinancierSyncStatus });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: fniTOFinancierSyncStatus }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(fniTOFinancierSyncStatusService.update).toHaveBeenCalledWith(fniTOFinancierSyncStatus);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<FniTOFinancierSyncStatus>>();
      const fniTOFinancierSyncStatus = new FniTOFinancierSyncStatus();
      jest.spyOn(fniTOFinancierSyncStatusService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fniTOFinancierSyncStatus });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: fniTOFinancierSyncStatus }));
      saveSubject.complete();

      // THEN
      expect(fniTOFinancierSyncStatusService.create).toHaveBeenCalledWith(fniTOFinancierSyncStatus);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<FniTOFinancierSyncStatus>>();
      const fniTOFinancierSyncStatus = { id: 123 };
      jest.spyOn(fniTOFinancierSyncStatusService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fniTOFinancierSyncStatus });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(fniTOFinancierSyncStatusService.update).toHaveBeenCalledWith(fniTOFinancierSyncStatus);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
