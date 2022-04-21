import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { OppurtunityDetailsComponent } from '../list/oppurtunity-details.component';
import { OppurtunityDetailsDetailComponent } from '../detail/oppurtunity-details-detail.component';
import { OppurtunityDetailsUpdateComponent } from '../update/oppurtunity-details-update.component';
import { OppurtunityDetailsRoutingResolveService } from './oppurtunity-details-routing-resolve.service';

const oppurtunityDetailsRoute: Routes = [
  {
    path: '',
    component: OppurtunityDetailsComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OppurtunityDetailsDetailComponent,
    resolve: {
      oppurtunityDetails: OppurtunityDetailsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OppurtunityDetailsUpdateComponent,
    resolve: {
      oppurtunityDetails: OppurtunityDetailsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OppurtunityDetailsUpdateComponent,
    resolve: {
      oppurtunityDetails: OppurtunityDetailsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(oppurtunityDetailsRoute)],
  exports: [RouterModule],
})
export class OppurtunityDetailsRoutingModule {}
