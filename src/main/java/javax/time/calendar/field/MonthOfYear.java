/*
 * Copyright (c) 2007-2009 Stephen Colebourne & Michael Nascimento Santos
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * Neither the name of JSR-310 nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package javax.time.calendar.field;

import java.util.Locale;

import javax.time.calendar.Calendrical;
import javax.time.calendar.CalendricalProvider;
import javax.time.calendar.DateAdjuster;
import javax.time.calendar.DateMatcher;
import javax.time.calendar.DateProvider;
import javax.time.calendar.DateResolver;
import javax.time.calendar.DateResolvers;
import javax.time.calendar.DateTimeFieldRule;
import javax.time.calendar.ISOChronology;
import javax.time.calendar.IllegalCalendarFieldValueException;
import javax.time.calendar.LocalDate;
import javax.time.calendar.format.DateTimeFormatSymbols;
import javax.time.calendar.format.DateTimeFormatterBuilder.TextStyle;

/**
 * A representation of a month of year in the ISO-8601 calendar system.
 * <p>
 * MonthOfYear is an immutable time field that can only store a month of year.
 * It is a type-safe way of representing a month of year in an application.
 * <p>
 * <b>Do not use ordinal() to obtain the numeric representation of a MonthOfYear
 * instance. Use getValue() instead.</b>
 * <p>
 * MonthOfYear is immutable and thread-safe.
 *
 * @author Michael Nascimento Santos
 * @author Stephen Colebourne
 */
public enum MonthOfYear
        implements CalendricalProvider, DateAdjuster, DateMatcher {

    /**
     * The singleton instance for the month of January.
     */
    JANUARY(1),
    /**
     * The singleton instance for the month of February.
     */
    FEBRUARY(2),
    /**
     * The singleton instance for the month of March.
     */
    MARCH(3),
    /**
     * The singleton instance for the month of April.
     */
    APRIL(4),
    /**
     * The singleton instance for the month of May.
     */
    MAY(5),
    /**
     * The singleton instance for the month of June.
     */
    JUNE(6),
    /**
     * The singleton instance for the month of July.
     */
    JULY(7),
    /**
     * The singleton instance for the month of August.
     */
    AUGUST(8),
    /**
     * The singleton instance for the month of September.
     */
    SEPTEMBER(9),
    /**
     * The singleton instance for the month of October.
     */
    OCTOBER(10),
    /**
     * The singleton instance for the month of November.
     */
    NOVEMBER(11),
    /**
     * The singleton instance for the month of December.
     */
    DECEMBER(12),
    ;

    /**
     * The month of year being represented.
     */
    private final int monthOfYear;

    //-----------------------------------------------------------------------
    /**
     * Gets the rule that defines how the month of year field operates.
     * <p>
     * The rule provides access to the minimum and maximum values, and a
     * generic way to access values within a calendrical.
     *
     * @return the month of year rule, never null
     */
    public static DateTimeFieldRule rule() {
        return ISOChronology.monthOfYearRule();
    }

    //-----------------------------------------------------------------------
    /**
     * Obtains an instance of <code>MonthOfYear</code> from a value.
     * <p>
     * Each month has an associated value, which ranges from 1 (January) to
     * 12 (December).
     *
     * @param monthOfYear  the month of year to represent, from 1 (January) to 12 (December)
     * @return the MonthOfYear singleton, never null
     * @throws IllegalCalendarFieldValueException if the month of year is invalid
     */
    public static MonthOfYear monthOfYear(int monthOfYear) {
        switch (monthOfYear) {
            case 1:
                return JANUARY;
            case 2:
                return FEBRUARY;
            case 3:
                return MARCH;
            case 4:
                return APRIL;
            case 5:
                return MAY;
            case 6:
                return JUNE;
            case 7:
                return JULY;
            case 8:
                return AUGUST;
            case 9:
                return SEPTEMBER;
            case 10:
                return OCTOBER;
            case 11:
                return NOVEMBER;
            case 12:
                return DECEMBER;
            default:
                throw new IllegalCalendarFieldValueException(rule(), monthOfYear, 1, 12);
        }
    }

    /**
     * Obtains an instance of <code>MonthOfYear</code> from a date provider.
     * <p>
     * This can be used extract a month of year object directly from any implementation
     * of DateProvider, including those in other calendar systems.
     *
     * @param dateProvider  the date provider to use, not null
     * @return the MonthOfYear singleton, never null
     */
    public static MonthOfYear monthOfYear(DateProvider dateProvider) {
        return LocalDate.date(dateProvider).getMonthOfYear();
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance with the specified month of year.
     *
     * @param monthOfYear  the month of year to represent
     */
    private MonthOfYear(int monthOfYear) {
        this.monthOfYear = monthOfYear;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the month of year value.
     *
     * @return the month of year, from 1 (January) to 12 (December)
     */
    public int getValue() {
        return monthOfYear;
    }

    /**
     * Gets the month of year value as short text.
     * <p>
     * In English, this will return text of the form 'Jan' or 'Dec'.
     * <p>
     * If there is no textual mapping for the locale, then the value is
     * returned as per {@link Integer#toString()}.
     *
     * @param locale  the locale to use, not null
     * @return the short text value of the month of year, never null
     */
    public String getShortText(Locale locale) {
        DateTimeFormatSymbols symbols = DateTimeFormatSymbols.getInstance(locale);
        String text = symbols.getFieldValueText(rule(), TextStyle.SHORT, monthOfYear);
        return text == null ? Integer.toString(monthOfYear) : text;
    }

    /**
     * Gets the month of year value as text.
     * <p>
     * In English, this will return text of the form 'January' or 'December'.
     * <p>
     * If there is no textual mapping for the locale, then the value is
     * returned as per {@link Integer#toString()}.
     *
     * @param locale  the locale to use, not null
     * @return the long text value of the month of year, never null
     */
    public String getText(Locale locale) {
        DateTimeFormatSymbols symbols = DateTimeFormatSymbols.getInstance(locale);
        String text = symbols.getFieldValueText(rule(), TextStyle.FULL, monthOfYear);
        return text == null ? Integer.toString(monthOfYear) : text;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the next month of year wrapping so that the next month of year
     * is always returned.
     *
     * @return the next month of year, never null
     */
    public MonthOfYear next() {
        return values()[(ordinal() + 1) % 12];
    }

    /**
     * Gets the previous month of year wrapping so that the previous month of year
     * is always returned.
     *
     * @return the previous month of year, never null
     */
    public MonthOfYear previous() {
        return values()[(ordinal() + 12 - 1) % 12];
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the MonthOfYear which is the specified number of months after
     * this MonthOfYear.
     * <p>
     * The calculation wraps around the end of the year from December to January.
     * The days to add may be negative.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param months  the months to add, positive or negative
     * @return the resulting MonthOfYear, never null
     */
    public MonthOfYear plusMonths(int months) {
        return values()[(ordinal() + (months % 12)) % 12];
    }

    /**
     * Returns the MonthOfYear which is the specified number of days before
     * this MonthOfYear.
     * <p>
     * The calculation wraps around the start of the week from January to December.
     * The days to subtract may be negative.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param months  the months to subtract, positive or negative
     * @return the resulting MonthOfYear, never null
     */
    public MonthOfYear minusMonths(int months) {
        return values()[(ordinal() + 12 - (months % 12)) % 12];
    }

    //-----------------------------------------------------------------------
    /**
     * Adjusts a date to have the value of this month of year, returning a new date.
     * <p>
     * If the day of month is invalid for the new month then the
     * {@link DateResolvers#previousValid()} resolver is used.
     * <p>
     * For example, if this object represents November, and the input date is
     * the 31st December, then the result will be the 30th November. The result
     * cannot be the 31st of November, so the previous valid date is chosen.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param date  the date to be adjusted, not null
     * @return the adjusted date, never null
     */
    public LocalDate adjustDate(LocalDate date) {
        return adjustDate(date, DateResolvers.previousValid());
    }

    /**
     * Adjusts a date to have the value of this month of year, using a resolver to
     * handle the case when the day of month becomes invalid.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param date  the date to be adjusted, not null
     * @param resolver  the date resolver to use if the day of month becomes invalid, not null
     * @return the adjusted date, never null
     * @throws IllegalCalendarFieldValueException if the date cannot be resolved using the resolver
     */
    public LocalDate adjustDate(LocalDate date, DateResolver resolver) {
        return date.withMonthOfYear(monthOfYear, resolver);
    }

    /**
     * Checks if the value of this month of year matches the input date.
     *
     * @param date  the date to match, not null
     * @return true if the date matches, false otherwise
     */
    public boolean matchesDate(LocalDate date) {
        return date.getMonthOfYear() == this;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the length of this month in days.
     *
     * @param year  the year to obtain the length for, not null
     * @return the length of this month in days, from 28 to 31
     */
    public int lengthInDays(Year year) {
        if (year == null) {
            throw new NullPointerException("The year must not be null");
        }
        switch (this) {
            case FEBRUARY:
                return (year.isLeap() ? 29 : 28);
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return 30;
            default:
                return 31;
        }
    }

    /**
     * Gets the length of this month in days.
     *
     * @param year  the year to obtain the length for, from MIN_YEAR to MAX_YEAR
     * @return the length of this month in days, from 28 to 31
     * @throws IllegalCalendarFieldValueException if the year value is invalid
     */
    public int lengthInDays(int year) {
        ISOChronology.yearRule().checkValue(year);
        switch (this) {
            case FEBRUARY:
                return (ISOChronology.isLeapYear(year) ? 29 : 28);
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return 30;
            default:
                return 31;
        }
    }

    /**
     * Gets the minimum length of this month in days.
     *
     * @return the minimum length of this month in days, from 28 to 31
     */
    public int minLengthInDays() {
        switch (this) {
            case FEBRUARY:
                return 28;
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return 30;
            default:
                return 31;
        }
    }

    /**
     * Gets the maximum length of this month in days.
     *
     * @return the maximum length of this month in days, from 29 to 31
     */
    public int maxLengthInDays() {
        switch (this) {
            case FEBRUARY:
                return 29;
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return 30;
            default:
                return 31;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the last day of the month.
     *
     * @param year  the year to obtain the last day of month for, not null
     * @return an object representing the last day of this month
     */
    public DayOfMonth getLastDayOfMonth(Year year) {
        return DayOfMonth.dayOfMonth(lengthInDays(year));
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the quarter that this month falls in.
     *
     * @return the quarter of year, never null
     */
    public QuarterOfYear getQuarterOfYear() {
        if (ordinal() < 3) {
            return QuarterOfYear.Q1;
        } else if (ordinal() < 6) {
            return QuarterOfYear.Q2;
        } else if (ordinal() < 9) {
            return QuarterOfYear.Q3;
        } else {
            return QuarterOfYear.Q4;
        }
    }

    /**
     * Gets the index of the month within the quarter.
     *
     * @return the month of season, from 1 to 3
     */
    public int getMonthOfQuarter() {
        return (ordinal() % 3) + 1;
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this field to a <code>Calendrical</code>.
     *
     * @return the calendrical representation for this instance, never null
     */
    public Calendrical toCalendrical() {
        return new Calendrical(rule(), getValue());
    }

    //-----------------------------------------------------------------------
    /**
     * A string describing the month of year object.
     *
     * @return a string describing this object
     */
    @Override
    public String toString() {
        return "MonthOfYear=" + name();
    }

}
