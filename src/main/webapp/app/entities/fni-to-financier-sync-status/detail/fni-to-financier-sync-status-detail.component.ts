import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFniTOFinancierSyncStatus } from '../fni-to-financier-sync-status.model';

@Component({
  selector: 'jhi-fni-to-financier-sync-status-detail',
  templateUrl: './fni-to-financier-sync-status-detail.component.html',
})
export class FniTOFinancierSyncStatusDetailComponent implements OnInit {
  fniTOFinancierSyncStatus: IFniTOFinancierSyncStatus | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fniTOFinancierSyncStatus }) => {
      this.fniTOFinancierSyncStatus = fniTOFinancierSyncStatus;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
