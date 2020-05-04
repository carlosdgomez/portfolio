import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Portfolio } from '../models/portfolio.model';
import { environment } from 'src/environments/environment';
import { PortfolioRequest } from '../models/portfolio-request.model';

@Injectable({ providedIn: 'root' })
export class PortfolioService {

  private pageIndex: number;
  private pageSize: number;

  constructor(private http: HttpClient) {
    this.pageIndex = environment.page.index;
    this.pageSize = environment.page.size;
  }

  loadPortfolios(): Observable<Portfolio[]> {
    let params = new HttpParams()
      .set('page', String(this.pageIndex))
      .set('size', String(this.pageSize));
    
    return this.http.get<Portfolio[]>(environment.api.portfolio, { params });
  }

  loadNext(): Observable<Portfolio[]> {
    this.pageIndex++;
    return this.loadPortfolios();
  }

  loadPrev(): Observable<Portfolio[]> {
    this.pageIndex--;
    return this.loadPortfolios();
  }

  updatePortfolio(id: number, portfolioRequest: PortfolioRequest): Observable<void> {
    return this.http.put<void>(`${environment.api.portfolio}/${id}`, portfolioRequest);
  }

  getPageIndex(): number {
    return this.pageIndex;
  }
}
