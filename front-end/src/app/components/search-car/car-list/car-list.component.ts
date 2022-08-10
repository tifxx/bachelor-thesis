import { ReservationService } from 'src/app/service/reservation.service';
import { Car } from './../../../model/Car';
import { CarService } from '../../../service/car.service';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/model/User';
import { TokenService } from 'src/app/service/token.service';
import { ListKeyManager } from '@angular/cdk/a11y';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {

  cars!: Car[]
  pickUpDate: string | undefined
  dropOffDate: string | undefined
  user: User | undefined
  role: boolean | undefined
  totalDays: number | undefined
  

  constructor(private service: CarService, private route: ActivatedRoute, private router: Router, private token: TokenService, private reservationSerivce: ReservationService) {
    if (this.router.getCurrentNavigation()?.extras.state) {
      const navigation = this.router.getCurrentNavigation();
      const state = navigation?.extras.state as { cars: Car[], pickUpDate: string, dropOffDate: string };
      this.cars = state?.cars;
      this.pickUpDate = state?.pickUpDate
      this.dropOffDate = state?.dropOffDate
      let date_1 = new Date(this.pickUpDate)
      let date_2 = new Date(this.dropOffDate)
      let difference = date_2.getTime() - date_1.getTime()
      let days = Math.abs(Math.ceil(difference / (1000 * 3600 * 24)));
      this.cars.forEach(car => {
        car.totalPrice = days * car.priceForADay
        car.totalRating = (car.sumRating/car.numReviews).toPrecision(3)
       })
    } else {
      this.service.getCars().subscribe(car => {
        this.cars = car
        this.cars.forEach(car => {
          car.totalRating = (car.sumRating/car.numReviews).toPrecision(3)
           })
      });
    }
    this.user = this.token.getUser()
  }

  ngOnInit(): void {
  }

  change(value: string)
  {
    
  }

}
