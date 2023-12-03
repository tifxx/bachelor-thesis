import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.css']
})
export class FaqComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  collapseStates: boolean[] = [];

  toggleCollapse(index: number) {
    this.collapseStates[index] = !this.collapseStates[index];
  }

  isCollapseOpen(index: number) {
    return this.collapseStates[index];
  }
}
