import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/User';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  currentUser: User | undefined
  userId: number | undefined


  constructor(private tokenService: TokenService) { }

  ngOnInit(): void {
    this.userId = this.tokenService.getUser()?.id
    this.currentUser = this.tokenService.getUser()
  }

  onLogOut() {
    this.tokenService.signOut();
    window.location.href = '/login';
  }

}
