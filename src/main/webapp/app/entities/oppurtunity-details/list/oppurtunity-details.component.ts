import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOppurtunityDetails } from '../oppurtunity-details.model';
import { OppurtunityDetailsService } from '../service/oppurtunity-details.service';
import { OppurtunityDetailsDeleteDialogComponent } from '../delete/oppurtunity-details-delete-dialog.component';

@Component({
  selector: 'jhi-oppurtunity-details',
  templateUrl: './oppurtunity-details.component.html',
})
export class OppurtunityDetailsComponent implements OnInit {
  oppurtunityDetails?: IOppurtunityDetails[];
  isLoading = false;

  constructor(protected oppurtunityDetailsService: OppurtunityDetailsService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.oppurtunityDetailsService.query().subscribe({
      next: (res: HttpResponse<IOppurtunityDetails[]>) => {
        this.isLoading = false;
        this.oppurtunityDetails = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IOppurtunityDetails): number {
    return item.id!;
  }

  delete(oppurtunityDetails: IOppurtunityDetails): void {
    const modalRef = this.modalService.open(OppurtunityDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.oppurtunityDetails = oppurtunityDetails;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
