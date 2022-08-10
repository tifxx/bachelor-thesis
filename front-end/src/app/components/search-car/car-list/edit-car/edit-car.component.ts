import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { Router, RouterModule } from '@angular/router';
import { Car } from 'src/app/model/Car';
import { CarService } from 'src/app/service/car.service';

@Component({
  selector: 'app-edit-car',
  templateUrl: './edit-car.component.html',
  styleUrls: ['./edit-car.component.css']
})
export class EditCarComponent implements OnInit {
  priceForADay: number | undefined;
  withInsurance: boolean | undefined;
  withAirConditioner: boolean | undefined;
  car!: Car

  constructor(private service: CarService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.service.getCarWithId(+this.route.snapshot.paramMap.get('id')!).subscribe(c=>this.car=c)
  }

  onSubmit()
  {
    this.service.updateCar(this.car?.id, this.car.withInsurance, this.car.withAirConditioner, this.car.priceForADay)
    .subscribe(c=> this.router.navigate(['/cars']))
    
  }

  onChangedInsurance(entry: boolean) {
    this.car.withInsurance = entry
  }

  onChangedConditioner(entry: boolean) {
    this.car.withAirConditioner = entry
  }
}
