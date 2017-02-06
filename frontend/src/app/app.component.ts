import {Component} from "@angular/core";

@Component({
    selector: 'my-app',
    template: `
<h1>Hello {{name}}</h1>
<book-list></book-list>
`,
})
export class AppComponent {
    name = 'Angular';
}
