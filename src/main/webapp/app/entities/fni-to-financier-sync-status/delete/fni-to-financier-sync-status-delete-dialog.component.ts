import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IFniTOFinancierSyncStatus } from '../fni-to-financier-sync-status.model';
import { FniTOFinancierSyncStatusService } from '../service/fni-to-financier-sync-status.service';

@Component({
  templateUrl: './fni-to-financier-sync-status-delete-dialog.component.html',
})
export class FniTOFinancierSyncStatusDeleteDialogComponent {
  fniTOFinancierSyncStatus?: IFniTOFinancierSyncStatus;

  constructor(protected fniTOFinancierSyncStatusService: FniTOFinancierSyncStatusService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.fniTOFinancierSyncStatusService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
