import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { FniTOFinancierSyncStatusComponent } from '../list/fni-to-financier-sync-status.component';
import { FniTOFinancierSyncStatusDetailComponent } from '../detail/fni-to-financier-sync-status-detail.component';
import { FniTOFinancierSyncStatusUpdateComponent } from '../update/fni-to-financier-sync-status-update.component';
import { FniTOFinancierSyncStatusRoutingResolveService } from './fni-to-financier-sync-status-routing-resolve.service';

const fniTOFinancierSyncStatusRoute: Routes = [
  {
    path: '',
    component: FniTOFinancierSyncStatusComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FniTOFinancierSyncStatusDetailComponent,
    resolve: {
      fniTOFinancierSyncStatus: FniTOFinancierSyncStatusRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FniTOFinancierSyncStatusUpdateComponent,
    resolve: {
      fniTOFinancierSyncStatus: FniTOFinancierSyncStatusRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FniTOFinancierSyncStatusUpdateComponent,
    resolve: {
      fniTOFinancierSyncStatus: FniTOFinancierSyncStatusRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(fniTOFinancierSyncStatusRoute)],
  exports: [RouterModule],
})
export class FniTOFinancierSyncStatusRoutingModule {}
