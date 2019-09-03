import { Apollo } from 'apollo-angular';
import { Injectable } from '@angular/core';
import { Model } from './models/model';
import { ApiClient } from './apis/api-client';

@Injectable({
  providedIn: 'root'
})
export class AppInitializerService {

  constructor(private apollo: Apollo) { }

  init() {
    Model.setApiClient(new ApiClient(this.apollo));
  }
}
