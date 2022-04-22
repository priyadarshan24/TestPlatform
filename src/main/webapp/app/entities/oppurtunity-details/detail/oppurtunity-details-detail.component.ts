import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOppurtunityDetails } from '../oppurtunity-details.model';

@Component({
  selector: 'jhi-oppurtunity-details-detail',
  templateUrl: './oppurtunity-details-detail.component.html',
})
export class OppurtunityDetailsDetailComponent implements OnInit {
  oppurtunityDetails: IOppurtunityDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ oppurtunityDetails }) => {
      this.oppurtunityDetails = oppurtunityDetails;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
