import { WriteReviewComponent } from './components/reservation-list/write-review/write-review.component';
import { ChangeStatusComponent } from './components/reservation-list/change-status/change-status.component';
import { ReservationListComponent } from './components/reservation-list/reservation-list.component';
import { RentComponent } from './components/search-car/car-list/rent/rent.component';
import { AddCarComponent } from './components/search-car/add-car/add-car.component';
import { CarListComponent } from './components/search-car/car-list/car-list.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LocationsComponent } from './components/locations/locations.component';
import { SearchCarComponent } from './components/search-car/search-car.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { LogInComponent } from './components/log-in/log-in.component';
import { EditCarComponent } from './components/search-car/car-list/edit-car/edit-car.component';
import { NewLocationComponent } from './components/locations/new-location/new-location.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'cars', component: CarListComponent },
  { path: 'add-car', component: AddCarComponent },
  { path: 'locations', component: LocationsComponent },
  { path: 'search', component: SearchCarComponent },
  { path: 'signin', component: SignInComponent },
  { path: 'login', component: LogInComponent },
  { path: 'rent/:id/:pickUpDate/:dropOffDate', component: RentComponent },
  { path: 'edit/:id', component: EditCarComponent },
  { path: 'reservations', component: ReservationListComponent },
  { path: 'status/:id', component: ChangeStatusComponent },
  { path: 'review/:resId/:carId', component: WriteReviewComponent },
  {path: 'newLocation', component:NewLocationComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
