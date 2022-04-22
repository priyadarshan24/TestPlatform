import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IOppurtunityDetails, OppurtunityDetails } from '../oppurtunity-details.model';
import { OppurtunityDetailsService } from '../service/oppurtunity-details.service';
import { IFniTOFinancierSyncStatus } from 'app/entities/fni-to-financier-sync-status/fni-to-financier-sync-status.model';
import { FniTOFinancierSyncStatusService } from 'app/entities/fni-to-financier-sync-status/service/fni-to-financier-sync-status.service';

@Component({
  selector: 'jhi-oppurtunity-details-update',
  templateUrl: './oppurtunity-details-update.component.html',
})
export class OppurtunityDetailsUpdateComponent implements OnInit {
  isSaving = false;

  financierSyncStatusesCollection: IFniTOFinancierSyncStatus[] = [];

  editForm = this.fb.group({
    id: [],
    crmOppurtunityID: [],
    oppurtunityCreatedDate: [],
    crmCustomerID: [],
    bdmName: [],
    bdmID: [],
    dseID: [],
    dseName: [],
    accountType: [],
    accountName: [],
    accountSite: [],
    vehicleClass: [],
    vehicleVariant: [],
    engineCapacity: [],
    fuelTankCapacity: [],
    wheelBase: [],
    power: [],
    gvwWeight: [],
    payloadWeight: [],
    exShowRoomPrice: [],
    onRoadPrice: [],
    lob: [],
    ppl: [],
    pl: [],
    financierSyncStatus: [],
  });

  constructor(
    protected oppurtunityDetailsService: OppurtunityDetailsService,
    protected fniTOFinancierSyncStatusService: FniTOFinancierSyncStatusService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ oppurtunityDetails }) => {
      this.updateForm(oppurtunityDetails);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const oppurtunityDetails = this.createFromForm();
    if (oppurtunityDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.oppurtunityDetailsService.update(oppurtunityDetails));
    } else {
      this.subscribeToSaveResponse(this.oppurtunityDetailsService.create(oppurtunityDetails));
    }
  }

  trackFniTOFinancierSyncStatusById(_index: number, item: IFniTOFinancierSyncStatus): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOppurtunityDetails>>): void {
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

  protected updateForm(oppurtunityDetails: IOppurtunityDetails): void {
    this.editForm.patchValue({
      id: oppurtunityDetails.id,
      crmOppurtunityID: oppurtunityDetails.crmOppurtunityID,
      oppurtunityCreatedDate: oppurtunityDetails.oppurtunityCreatedDate,
      crmCustomerID: oppurtunityDetails.crmCustomerID,
      bdmName: oppurtunityDetails.bdmName,
      bdmID: oppurtunityDetails.bdmID,
      dseID: oppurtunityDetails.dseID,
      dseName: oppurtunityDetails.dseName,
      accountType: oppurtunityDetails.accountType,
      accountName: oppurtunityDetails.accountName,
      accountSite: oppurtunityDetails.accountSite,
      vehicleClass: oppurtunityDetails.vehicleClass,
      vehicleVariant: oppurtunityDetails.vehicleVariant,
      engineCapacity: oppurtunityDetails.engineCapacity,
      fuelTankCapacity: oppurtunityDetails.fuelTankCapacity,
      wheelBase: oppurtunityDetails.wheelBase,
      power: oppurtunityDetails.power,
      gvwWeight: oppurtunityDetails.gvwWeight,
      payloadWeight: oppurtunityDetails.payloadWeight,
      exShowRoomPrice: oppurtunityDetails.exShowRoomPrice,
      onRoadPrice: oppurtunityDetails.onRoadPrice,
      lob: oppurtunityDetails.lob,
      ppl: oppurtunityDetails.ppl,
      pl: oppurtunityDetails.pl,
      financierSyncStatus: oppurtunityDetails.financierSyncStatus,
    });

    this.financierSyncStatusesCollection = this.fniTOFinancierSyncStatusService.addFniTOFinancierSyncStatusToCollectionIfMissing(
      this.financierSyncStatusesCollection,
      oppurtunityDetails.financierSyncStatus
    );
  }

  protected loadRelationshipsOptions(): void {
    this.fniTOFinancierSyncStatusService
      .query({ filter: 'oppurtunitydetails-is-null' })
      .pipe(map((res: HttpResponse<IFniTOFinancierSyncStatus[]>) => res.body ?? []))
      .pipe(
        map((fniTOFinancierSyncStatuses: IFniTOFinancierSyncStatus[]) =>
          this.fniTOFinancierSyncStatusService.addFniTOFinancierSyncStatusToCollectionIfMissing(
            fniTOFinancierSyncStatuses,
            this.editForm.get('financierSyncStatus')!.value
          )
        )
      )
      .subscribe(
        (fniTOFinancierSyncStatuses: IFniTOFinancierSyncStatus[]) => (this.financierSyncStatusesCollection = fniTOFinancierSyncStatuses)
      );
  }

  protected createFromForm(): IOppurtunityDetails {
    return {
      ...new OppurtunityDetails(),
      id: this.editForm.get(['id'])!.value,
      crmOppurtunityID: this.editForm.get(['crmOppurtunityID'])!.value,
      oppurtunityCreatedDate: this.editForm.get(['oppurtunityCreatedDate'])!.value,
      crmCustomerID: this.editForm.get(['crmCustomerID'])!.value,
      bdmName: this.editForm.get(['bdmName'])!.value,
      bdmID: this.editForm.get(['bdmID'])!.value,
      dseID: this.editForm.get(['dseID'])!.value,
      dseName: this.editForm.get(['dseName'])!.value,
      accountType: this.editForm.get(['accountType'])!.value,
      accountName: this.editForm.get(['accountName'])!.value,
      accountSite: this.editForm.get(['accountSite'])!.value,
      vehicleClass: this.editForm.get(['vehicleClass'])!.value,
      vehicleVariant: this.editForm.get(['vehicleVariant'])!.value,
      engineCapacity: this.editForm.get(['engineCapacity'])!.value,
      fuelTankCapacity: this.editForm.get(['fuelTankCapacity'])!.value,
      wheelBase: this.editForm.get(['wheelBase'])!.value,
      power: this.editForm.get(['power'])!.value,
      gvwWeight: this.editForm.get(['gvwWeight'])!.value,
      payloadWeight: this.editForm.get(['payloadWeight'])!.value,
      exShowRoomPrice: this.editForm.get(['exShowRoomPrice'])!.value,
      onRoadPrice: this.editForm.get(['onRoadPrice'])!.value,
      lob: this.editForm.get(['lob'])!.value,
      ppl: this.editForm.get(['ppl'])!.value,
      pl: this.editForm.get(['pl'])!.value,
      financierSyncStatus: this.editForm.get(['financierSyncStatus'])!.value,
    };
  }
}
