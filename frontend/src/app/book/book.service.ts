import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Book} from "./book";
import {Observable} from "rxjs/Rx";
import "rxjs/add/operator/catch";
import "rxjs/add/operator/map";

@Injectable()
export class BooksService {
    private booksUrl = 'api/books/'; // TODO take real server url
    //private booksUrl = 'app/book/dummybooks.json'; // TODO remove me

    constructor(private http: Http) {}

    getBooks(): Observable<Book[]> {
        return this.http.get(this.booksUrl)
            .map(res => res.json())
            .catch(this.handleError);
    }

    getBook(bookId: string): Observable<Book> {
        return this.http.get(this.booksUrl + `${bookId}`)
            .map(this.extractData)
            .catch(this.handleError);
    }

    private extractData(res: Response) {
        let body = res.json();
        return body.data || {};
    }

    private handleError(error: Response | any) {
        // In a real world app, we might use a remote logging infrastructure
        let errMsg: string;
        if (error instanceof Response) {
            const body = error.json() || '';
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Observable.throw(errMsg);
    }
}
