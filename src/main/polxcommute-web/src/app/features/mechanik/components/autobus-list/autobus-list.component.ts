import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Autobus} from "../../../../api/models/autobus";
import {MatListModule} from "@angular/material/list";

@Component({
  selector: 'app-autobus-list',
  standalone: true,
  imports: [MatListModule],
  templateUrl: './autobus-list.component.html',
  styleUrl: './autobus-list.component.css'
})
export class AutobusListComponent implements OnInit {
  @Input({required: true}) public autobusList: Autobus[] = [];
  @Output() public readonly onAutobusSelect: EventEmitter<number> = new EventEmitter<number>();

  public ngOnInit() {
    if(this.autobusList.length) {
      this.onAutobusSelect.emit(this.autobusList[0].id)
    }
  }
}
