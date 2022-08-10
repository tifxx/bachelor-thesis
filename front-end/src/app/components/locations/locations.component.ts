import { LocationService } from './../../service/location.service';
import { Component, OnInit } from '@angular/core';
import { ILocation } from 'src/app/model/ILocation';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { TokenService } from 'src/app/service/token.service';
import { User } from 'src/app/model/User';

@Component({
  selector: 'app-locations',
  templateUrl: './locations.component.html',
  styleUrls: ['./locations.component.css']
})
export class LocationsComponent implements OnInit {

  locations: ILocation[] | undefined
  currentLocation: ILocation | undefined
  iframeSrc: string | undefined
  trustedUrl: SafeUrl | undefined
  user: User|undefined

  constructor(private service: LocationService, private sanitizer: DomSanitizer, private token: TokenService) { 
    this.service.getLocations().subscribe(l => { this.locations = l });
    this.iframeSrc = 'https://www.openstreetmap.org/export/embed.html?bbox=21.333389282226562%2C41.789232915019845%2C21.910171508789066%2C42.13133052651052&layer=mapnik'
    this.trustedUrl = this.sanitizeUrl(this.iframeSrc)
  }

  ngOnInit(): void {
    this.user=this.token.getUser()
  }

  clickedLocation(location: ILocation) {
    this.currentLocation = location
    this.iframeSrc = `https://www.openstreetmap.org/export/embed.html?bbox=21.333389282226562%2C41.789232915019845%2C21.910171508789066%2C42.13133052651052&layer=mapnik&marker=${this.currentLocation.latitude}%2C${this.currentLocation.longitude}`
    this.trustedUrl = this.sanitizeUrl(this.iframeSrc)
    console.log(this.trustedUrl)
  }

  sanitizeUrl(original: string) {
    return this.sanitizer.bypassSecurityTrustResourceUrl(original)
  }

}
