import { FormControl } from '@angular/forms';
import { finalize } from 'rxjs/operators';
import { Painel } from './painel.model';
import { PainelService } from './painel.service';
import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { ChartOptions } from 'chart.js';
import { MatDatepicker } from '@angular/material/datepicker';
import {MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS} from '@angular/material-moment-adapter';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';

import * as _moment from 'moment';
import {default as _rollupMoment, Moment} from 'moment';

const moment = _rollupMoment || _moment;

export const MY_FORMATS = {
  parse: {
    dateInput: 'MM/YYYY',
  },
  display: {
    dateInput: 'MM/YYYY',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};

@Component({
  selector: 'app-painel',
  templateUrl: './painel.component.html',
  styleUrls: ['./painel.component.scss'],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
    },

    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
})
export class PainelComponent implements OnInit {
  painel!: Painel;
  carregando = false;
  chartLegend = true;
  chartPlugins = [];

  chartOptions: ChartOptions<'pie'> = {
    responsive: false,
  };

  chartLabels = ['Ganhos', 'Despesas'];

  chartDatasets = [
    { data: [ 0 ] }
  ];

  data!: FormControl;

  constructor(
    private painelService: PainelService,
    private spinner: NgxSpinnerService
  ) {}

  ngOnInit(): void {
    this.carregarPainel();
    this.data = new FormControl(moment());
  }

  setMonthAndYear(normalizedMonthAndYear: Moment, datepicker: MatDatepicker<Moment>) {
    const ctrlValue = this.data.value!;
    ctrlValue.month(normalizedMonthAndYear.month());
    ctrlValue.year(normalizedMonthAndYear.year());
    this.data.setValue(ctrlValue);
    datepicker.close();
  }

  carregarPainel() {
    this.carregando = true;
    this.spinner.show();

    this.painelService
      .painelControleFinanceiro(this.data?.value?.format('YYYY-MM'))
      .pipe(
        finalize(() => {
          this.carregando = false;
          this.spinner.hide();
        })
      )
      .subscribe((result) => {
        this.painel = result;
        this.chartDatasets = [
          {data: [this.painel.totalGanho, this.painel.totalDespesa]}
        ];
      });
  }
}
