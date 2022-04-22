import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IOppurtunityDetails } from '../oppurtunity-details.model';
import { OppurtunityDetailsService } from '../service/oppurtunity-details.service';

@Component({
  templateUrl: './oppurtunity-details-delete-dialog.component.html',
})
export class OppurtunityDetailsDeleteDialogComponent {
  oppurtunityDetails?: IOppurtunityDetails;

  constructor(protected oppurtunityDetailsService: OppurtunityDetailsService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.oppurtunityDetailsService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
