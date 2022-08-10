import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CarListComponent } from './components/search-car/car-list/car-list.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { LocationsComponent } from './components/locations/locations.component';
import { AddCarComponent } from './components/search-car/add-car/add-car.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SearchCarComponent } from './components/search-car/search-car.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { LogInComponent } from './components/log-in/log-in.component';
import { RentComponent } from './components/search-car/car-list/rent/rent.component';
import { ReservationListComponent } from './components/reservation-list/reservation-list.component';
import { ChangeStatusComponent } from './components/reservation-list/change-status/change-status.component';
import { EditCarComponent } from './components/search-car/car-list/edit-car/edit-car.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { WriteReviewComponent } from './components/reservation-list/write-review/write-review.component';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { RatingComponent } from './components/reservation-list/write-review/rating/rating.component';
import { NewLocationComponent } from './components/locations/new-location/new-location.component';


@NgModule({
  declarations: [
    AppComponent,
    CarListComponent,
    HeaderComponent,
    HomeComponent,
    LocationsComponent,
    AddCarComponent,
    SearchCarComponent,
    SignInComponent,
    LogInComponent,
    RentComponent,
    ReservationListComponent,
    ChangeStatusComponent,
    EditCarComponent,
    WriteReviewComponent,
    RatingComponent,
    NewLocationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
