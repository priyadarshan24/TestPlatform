import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IFniTOFinancierSyncStatus, FniTOFinancierSyncStatus } from '../fni-to-financier-sync-status.model';
import { FniTOFinancierSyncStatusService } from '../service/fni-to-financier-sync-status.service';
import { SyncStatus } from 'app/entities/enumerations/sync-status.model';

@Component({
  selector: 'jhi-fni-to-financier-sync-status-update',
  templateUrl: './fni-to-financier-sync-status-update.component.html',
})
export class FniTOFinancierSyncStatusUpdateComponent implements OnInit {
  isSaving = false;
  syncStatusValues = Object.keys(SyncStatus);

  editForm = this.fb.group({
    id: [],
    syncDateTimeStamp: [],
    syncStatus: [],
    comments: [],
  });

  constructor(
    protected fniTOFinancierSyncStatusService: FniTOFinancierSyncStatusService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fniTOFinancierSyncStatus }) => {
      this.updateForm(fniTOFinancierSyncStatus);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fniTOFinancierSyncStatus = this.createFromForm();
    if (fniTOFinancierSyncStatus.id !== undefined) {
      this.subscribeToSaveResponse(this.fniTOFinancierSyncStatusService.update(fniTOFinancierSyncStatus));
    } else {
      this.subscribeToSaveResponse(this.fniTOFinancierSyncStatusService.create(fniTOFinancierSyncStatus));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFniTOFinancierSyncStatus>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(fniTOFinancierSyncStatus: IFniTOFinancierSyncStatus): void {
    this.editForm.patchValue({
      id: fniTOFinancierSyncStatus.id,
      syncDateTimeStamp: fniTOFinancierSyncStatus.syncDateTimeStamp,
      syncStatus: fniTOFinancierSyncStatus.syncStatus,
      comments: fniTOFinancierSyncStatus.comments,
    });
  }

  protected createFromForm(): IFniTOFinancierSyncStatus {
    return {
      ...new FniTOFinancierSyncStatus(),
      id: this.editForm.get(['id'])!.value,
      syncDateTimeStamp: this.editForm.get(['syncDateTimeStamp'])!.value,
      syncStatus: this.editForm.get(['syncStatus'])!.value,
      comments: this.editForm.get(['comments'])!.value,
    };
  }
}
