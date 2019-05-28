import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  moduleId: module.id,
  selector: 'countdown',
  templateUrl: 'countdown.html',
  styleUrls: ['countdown.css']
})

export class CountDown {
  @Input() units: any;
  @Input() end: any;
  @Input() displayString: String = '';
  @Input() text: any;
  @Input() divider: any;
  @Input() showZero: Boolean = false;
  @Output() reached: EventEmitter<Date> = new EventEmitter();
  display: any = [];
  displayNumbers: any = [];
  wasReached: Boolean = false;

  constructor() {
    setInterval(() => this._displayString(), 100);
  }

  _displayString() {

    if (typeof this.units === 'string') {
      this.units = this.units.split('|');
    }

    let givenDate: any = new Date(this.end);
    let now: any = new Date();
    let dateDifference: any = givenDate - now;

    if (dateDifference < 100 && dateDifference > 0 && !this.wasReached) {
      this.wasReached = true;
      this.reached.next(now);
    }

    if (dateDifference <= 0) {
      this.units = null;
    }

    if (this.units !== null) {
      let lastUnit = this.units[this.units.length - 1],
        unitConstantForMillisecs = {
          year: (((1000 * 60 * 60 * 24 * 7) * 4) * 12),
          month: ((1000 * 60 * 60 * 24 * 7) * 4),
          weeks: (1000 * 60 * 60 * 24 * 7),
          days: (1000 * 60 * 60 * 24),
          hours: (1000 * 60 * 60),
          minutes: (1000 * 60),
          seconds: 1000
        },
        unitsLeft = {},
        returnText = '',
        returnNumbers = '',
        totalMillisecsLeft = dateDifference,
        i,
        unit: any;

      for (i in this.units) {
        if (this.units.hasOwnProperty(i)) {

          unit = this.units[i].trim();
          if (unitConstantForMillisecs[unit.toLowerCase()] === false) {
            //$interval.cancel(countDownInterval);
            throw new Error('Cannot repeat unit: ' + unit);

          }
          if (unitConstantForMillisecs.hasOwnProperty(unit.toLowerCase()) === false) {
            throw new Error('Unit: ' + unit + ' is not supported. Please use following units: year, month, weeks, days, hours, minutes, seconds, milliseconds');
          }

          unitsLeft[unit] = totalMillisecsLeft / unitConstantForMillisecs[unit.toLowerCase()];

          if (lastUnit === unit) {
            unitsLeft[unit] = Math.ceil(unitsLeft[unit]);
          } else {
            unitsLeft[unit] = Math.floor(unitsLeft[unit]);
          }

          totalMillisecsLeft -= unitsLeft[unit] * unitConstantForMillisecs[unit.toLowerCase()];
          unitConstantForMillisecs[unit.toLowerCase()] = false;

          returnNumbers += ' ' + unitsLeft[unit] + ' | ';
          returnText += ' ' + unit;
        }
      }

      if (this.text === null || !this.text) {
        this.text = {
          Year: 'Year(s)',
          Month: 'Month(s)',
          Weeks: 'Week(s)',
          Days: 'Day(s)',
          Hours: 'Hour(s)',
          Minutes: 'Minute(s)',
          Seconds: 'Second(s)',
          MilliSeconds: 'Millisecond(s)x  '
        };
      }

      this.displayString = returnText
        .replace(' Year', ' '+ this.text.Year + ' | ')
        .replace(' Month', this.text.Month + ' | ')
        .replace(' Weeks', this.text.Weeks + ' | ')
        .replace(' Days', this.text.Days + ' | ')
        .replace(' Hours', this.text.Hours + ' | ')
        .replace(' Minutes', this.text.Minutes + ' | ')
        .replace(' Seconds', this.text.Seconds);

      this.displayNumbers = returnNumbers.split('|');
      this.display = this.displayString.split('|');
    }
  }
}

