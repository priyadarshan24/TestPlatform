import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFniTOFinancierSyncStatus, FniTOFinancierSyncStatus } from '../fni-to-financier-sync-status.model';
import { FniTOFinancierSyncStatusService } from '../service/fni-to-financier-sync-status.service';

@Injectable({ providedIn: 'root' })
export class FniTOFinancierSyncStatusRoutingResolveService implements Resolve<IFniTOFinancierSyncStatus> {
  constructor(protected service: FniTOFinancierSyncStatusService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFniTOFinancierSyncStatus> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((fniTOFinancierSyncStatus: HttpResponse<FniTOFinancierSyncStatus>) => {
          if (fniTOFinancierSyncStatus.body) {
            return of(fniTOFinancierSyncStatus.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FniTOFinancierSyncStatus());
  }
}
