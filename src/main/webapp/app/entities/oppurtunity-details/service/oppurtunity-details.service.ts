import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IOppurtunityDetails, getOppurtunityDetailsIdentifier } from '../oppurtunity-details.model';

export type EntityResponseType = HttpResponse<IOppurtunityDetails>;
export type EntityArrayResponseType = HttpResponse<IOppurtunityDetails[]>;

@Injectable({ providedIn: 'root' })
export class OppurtunityDetailsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/oppurtunity-details');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(oppurtunityDetails: IOppurtunityDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(oppurtunityDetails);
    return this.http
      .post<IOppurtunityDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(oppurtunityDetails: IOppurtunityDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(oppurtunityDetails);
    return this.http
      .put<IOppurtunityDetails>(`${this.resourceUrl}/${getOppurtunityDetailsIdentifier(oppurtunityDetails) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(oppurtunityDetails: IOppurtunityDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(oppurtunityDetails);
    return this.http
      .patch<IOppurtunityDetails>(`${this.resourceUrl}/${getOppurtunityDetailsIdentifier(oppurtunityDetails) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOppurtunityDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOppurtunityDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addOppurtunityDetailsToCollectionIfMissing(
    oppurtunityDetailsCollection: IOppurtunityDetails[],
    ...oppurtunityDetailsToCheck: (IOppurtunityDetails | null | undefined)[]
  ): IOppurtunityDetails[] {
    const oppurtunityDetails: IOppurtunityDetails[] = oppurtunityDetailsToCheck.filter(isPresent);
    if (oppurtunityDetails.length > 0) {
      const oppurtunityDetailsCollectionIdentifiers = oppurtunityDetailsCollection.map(
        oppurtunityDetailsItem => getOppurtunityDetailsIdentifier(oppurtunityDetailsItem)!
      );
      const oppurtunityDetailsToAdd = oppurtunityDetails.filter(oppurtunityDetailsItem => {
        const oppurtunityDetailsIdentifier = getOppurtunityDetailsIdentifier(oppurtunityDetailsItem);
        if (oppurtunityDetailsIdentifier == null || oppurtunityDetailsCollectionIdentifiers.includes(oppurtunityDetailsIdentifier)) {
          return false;
        }
        oppurtunityDetailsCollectionIdentifiers.push(oppurtunityDetailsIdentifier);
        return true;
      });
      return [...oppurtunityDetailsToAdd, ...oppurtunityDetailsCollection];
    }
    return oppurtunityDetailsCollection;
  }

  protected convertDateFromClient(oppurtunityDetails: IOppurtunityDetails): IOppurtunityDetails {
    return Object.assign({}, oppurtunityDetails, {
      oppurtunityCreatedDate: oppurtunityDetails.oppurtunityCreatedDate?.isValid()
        ? oppurtunityDetails.oppurtunityCreatedDate.format(DATE_FORMAT)
        : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.oppurtunityCreatedDate = res.body.oppurtunityCreatedDate ? dayjs(res.body.oppurtunityCreatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((oppurtunityDetails: IOppurtunityDetails) => {
        oppurtunityDetails.oppurtunityCreatedDate = oppurtunityDetails.oppurtunityCreatedDate
          ? dayjs(oppurtunityDetails.oppurtunityCreatedDate)
          : undefined;
      });
    }
    return res;
  }
}
