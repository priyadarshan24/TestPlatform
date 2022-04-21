import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFniTOFinancierSyncStatus } from '../fni-to-financier-sync-status.model';
import { FniTOFinancierSyncStatusService } from '../service/fni-to-financier-sync-status.service';
import { FniTOFinancierSyncStatusDeleteDialogComponent } from '../delete/fni-to-financier-sync-status-delete-dialog.component';

@Component({
  selector: 'jhi-fni-to-financier-sync-status',
  templateUrl: './fni-to-financier-sync-status.component.html',
})
export class FniTOFinancierSyncStatusComponent implements OnInit {
  fniTOFinancierSyncStatuses?: IFniTOFinancierSyncStatus[];
  isLoading = false;

  constructor(protected fniTOFinancierSyncStatusService: FniTOFinancierSyncStatusService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.fniTOFinancierSyncStatusService.query().subscribe({
      next: (res: HttpResponse<IFniTOFinancierSyncStatus[]>) => {
        this.isLoading = false;
        this.fniTOFinancierSyncStatuses = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IFniTOFinancierSyncStatus): number {
    return item.id!;
  }

  delete(fniTOFinancierSyncStatus: IFniTOFinancierSyncStatus): void {
    const modalRef = this.modalService.open(FniTOFinancierSyncStatusDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.fniTOFinancierSyncStatus = fniTOFinancierSyncStatus;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
