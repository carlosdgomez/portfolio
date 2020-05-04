import { Component, OnInit } from '@angular/core';

import { PortfolioService } from './core/services/portfolio.service';
import { Portfolio } from './core/models/portfolio.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  portfolios: Portfolio[];

  constructor(private portfolioService: PortfolioService) { }

  ngOnInit(): void {
    this.portfolioService.loadPortfolios().subscribe(
      (portfolios: Portfolio[]) => this.portfolios = portfolios
    );
  }
}
