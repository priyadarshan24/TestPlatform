import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { OppurtunityDetailsComponent } from './list/oppurtunity-details.component';
import { OppurtunityDetailsDetailComponent } from './detail/oppurtunity-details-detail.component';
import { OppurtunityDetailsUpdateComponent } from './update/oppurtunity-details-update.component';
import { OppurtunityDetailsDeleteDialogComponent } from './delete/oppurtunity-details-delete-dialog.component';
import { OppurtunityDetailsRoutingModule } from './route/oppurtunity-details-routing.module';

@NgModule({
  imports: [SharedModule, OppurtunityDetailsRoutingModule],
  declarations: [
    OppurtunityDetailsComponent,
    OppurtunityDetailsDetailComponent,
    OppurtunityDetailsUpdateComponent,
    OppurtunityDetailsDeleteDialogComponent,
  ],
  entryComponents: [OppurtunityDetailsDeleteDialogComponent],
})
export class OppurtunityDetailsModule {}
