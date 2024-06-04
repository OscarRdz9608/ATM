import { Component } from '@angular/core';
import { ListComponent } from "../list/list.component";

@Component({
    selector: 'app-start',
    standalone: true,
    templateUrl: './start.component.html',
    styleUrl: './start.component.css',
    imports: [ListComponent]
})
export class StartComponent {

}
