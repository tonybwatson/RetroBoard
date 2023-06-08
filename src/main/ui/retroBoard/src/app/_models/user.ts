import {Component, Input, OnInit} from "@angular/core";

@Component({
  template: './user/component.html',
  selector: 'app-users'
})
export class User implements OnInit {
  constructor() {
  }

  @Input() user: any[];

  ngOnInit() {
  }
}
