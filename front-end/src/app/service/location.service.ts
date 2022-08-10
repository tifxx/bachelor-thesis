import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ILocation } from '../model/ILocation';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(private http: HttpClient) { }

  getLocations(): Observable<ILocation[]> {
    return this.http.get<ILocation[]>('/api/locations');
  }

  addLocation(name: string, longitude: string, latitude: string): Observable<{}>
  {
    return this.http.post('/api/locations/addLocation',{
    name: name,
    longitude: longitude,
    latitude:latitude})
  }
}
