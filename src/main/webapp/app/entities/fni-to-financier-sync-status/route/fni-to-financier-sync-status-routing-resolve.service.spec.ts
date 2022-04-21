import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IFniTOFinancierSyncStatus, FniTOFinancierSyncStatus } from '../fni-to-financier-sync-status.model';
import { FniTOFinancierSyncStatusService } from '../service/fni-to-financier-sync-status.service';

import { FniTOFinancierSyncStatusRoutingResolveService } from './fni-to-financier-sync-status-routing-resolve.service';

describe('FniTOFinancierSyncStatus routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: FniTOFinancierSyncStatusRoutingResolveService;
  let service: FniTOFinancierSyncStatusService;
  let resultFniTOFinancierSyncStatus: IFniTOFinancierSyncStatus | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(FniTOFinancierSyncStatusRoutingResolveService);
    service = TestBed.inject(FniTOFinancierSyncStatusService);
    resultFniTOFinancierSyncStatus = undefined;
  });

  describe('resolve', () => {
    it('should return IFniTOFinancierSyncStatus returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultFniTOFinancierSyncStatus = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultFniTOFinancierSyncStatus).toEqual({ id: 123 });
    });

    it('should return new IFniTOFinancierSyncStatus if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultFniTOFinancierSyncStatus = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultFniTOFinancierSyncStatus).toEqual(new FniTOFinancierSyncStatus());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as FniTOFinancierSyncStatus })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultFniTOFinancierSyncStatus = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultFniTOFinancierSyncStatus).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
