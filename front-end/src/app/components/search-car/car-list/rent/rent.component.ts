import { TokenService } from './../../../../service/token.service';
import { Car } from './../../../../model/Car';
import { CarService } from './../../../../service/car.service';
import { LocationService } from './../../../../service/location.service';
import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReservationService } from 'src/app/service/reservation.service';
import { ILocation } from 'src/app/model/ILocation';

@Component({
  selector: 'app-rent',
  templateUrl: './rent.component.html',
  styleUrls: ['./rent.component.css']
})
export class RentComponent implements OnInit {

  carId!: number
  car: Car | undefined
  locations: ILocation[] | undefined
  pickUpLocation!: string
  dropOffLocation!: string
  pickUpDate!: string
  dropOffDate!: string
  totalDays!: number
  totalPrice!: number
  userId!: number

  constructor(private route: ActivatedRoute, private router: Router, private locationService: LocationService, private reservationService: ReservationService,
    private carService: CarService, private tokenService: TokenService) {
  }

  ngOnInit(): void {
    this.carId = +this.route.snapshot.paramMap.get('id')!
    this.pickUpDate = this.route.snapshot.paramMap.get('pickUpDate')!
    this.dropOffDate = this.route.snapshot.paramMap.get('dropOffDate')!
    this.totalDays = this.calculateTotalDays(this.pickUpDate, this.dropOffDate)
    this.locationService.getLocations().subscribe(l => { this.locations = l })
    this.carService.getCarWithId(this.carId).subscribe(c => { 
      this.car = c, 
      this.totalPrice = this.totalDays*c.priceForADay
    })

    this.userId = this.tokenService.getUser()!.id
  }

  onSubmit() {
    let pick = this.pickUpLocation.slice(1)
    let drop = this.dropOffLocation.slice(1) 
    this.reservationService.addNewReservation(pick, drop, this.totalPrice,
      this.carId, this.userId, this.pickUpDate, this.dropOffDate)
      .subscribe(r => {
        this.router.navigate(['/reservations']);
      })
  }

  calculateTotalDays(pickUp : string, dropOff : string) {
    let date_1 = new Date(pickUp)
    let date_2 = new Date(dropOff)
    let difference = date_2.getTime() - date_1.getTime()
    return Math.abs(Math.ceil(difference / (1000 * 3600 * 24)));
  }

}
