import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LocationService } from 'src/app/service/location.service';

@Component({
  selector: 'app-new-location',
  templateUrl: './new-location.component.html',
  styleUrls: ['./new-location.component.css']
})
export class NewLocationComponent implements OnInit {
  name!:string
  latitude!:string
  longitude!:string

  constructor(private service: LocationService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit()
  {
    this.service.addLocation(this.name, this.longitude, this.latitude).subscribe(c=>this.router.navigate(['/locations']))
  }

}
