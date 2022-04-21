import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOppurtunityDetails, OppurtunityDetails } from '../oppurtunity-details.model';
import { OppurtunityDetailsService } from '../service/oppurtunity-details.service';

@Injectable({ providedIn: 'root' })
export class OppurtunityDetailsRoutingResolveService implements Resolve<IOppurtunityDetails> {
  constructor(protected service: OppurtunityDetailsService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOppurtunityDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((oppurtunityDetails: HttpResponse<OppurtunityDetails>) => {
          if (oppurtunityDetails.body) {
            return of(oppurtunityDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OppurtunityDetails());
  }
}
