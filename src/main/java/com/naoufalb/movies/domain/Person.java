package com.naoufalb.movies.domain;

import com.naoufalb.movies.holder.ColumnNames;
import com.naoufalb.movies.holder.TableNames;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = TableNames.PERSONS)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@FieldDefaults(level = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = ColumnNames.PERSON_TYPE)
@Data
public class Person extends AbstractEntity {

    @Column(name = ColumnNames.PERSON_FIRST_NAME)
    String firstName;

    @Column(name = ColumnNames.PERSON_LAST_NAME)
    String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = ColumnNames.PERSON_BIRTH_DATE)
    LocalDate birthDate;

    @Column(name = ColumnNames.PERSON_NATIONALITY, length = 2)
    String nationality;
}
