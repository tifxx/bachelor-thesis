import { Component, OnInit } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';
import { filter } from 'rxjs';
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
  isFixed = false;

  constructor(private tokenService: TokenService, private router: Router) {
    router.events.pipe(filter(val => ((val instanceof NavigationStart)))).
      subscribe((val) => {

        if (val instanceof NavigationStart && ((val as NavigationStart).url === '/home')) {
          this.isFixed = true;
        }
        else {
          this.isFixed = false;
        }
      });
   }

  ngOnInit(): void {
    this.userId = this.tokenService.getUser()?.id
    this.currentUser = this.tokenService.getUser()
  }

  onLogOut() {
    this.tokenService.signOut();
    window.location.href = '/login';
  }

}
