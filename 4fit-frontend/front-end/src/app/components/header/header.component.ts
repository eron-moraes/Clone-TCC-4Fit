import { Component, EventEmitter, Output } from "@angular/core";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.scss"],
})
export class HeaderComponent {
  @Output() menu: EventEmitter<any> = new EventEmitter();
  closeMenu() {
    this.menu.emit();
  }
}
