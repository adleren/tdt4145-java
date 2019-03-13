/* FILL TRENINGSØKT */
INSERT INTO `magnram_db`.`Treningsøkt` (`TreningsøktID`, `Datotid`, `Varighet`, `Form`) VALUES ('1', '2019-04-12T00:00:00.000', '02:00:00', '3');
INSERT INTO `magnram_db`.`Treningsøkt` (`TreningsøktID`, `Datotid`, `Varighet`, `Form`) VALUES ('2', '2019-04-24T00:00:00.000', '02:00:00', '6');
INSERT INTO `magnram_db`.`Treningsøkt` (`TreningsøktID`, `Datotid`, `Varighet`, `Form`) VALUES ('3', '2019-04-08T00:00:00.000', '02:00:00', '2');
INSERT INTO `magnram_db`.`Treningsøkt` (`TreningsøktID`, `Datotid`, `Varighet`, `Form`) VALUES ('4', '2019-04-29T00:00:00.000', '02:00:00', '8');
INSERT INTO `magnram_db`.`Treningsøkt` (`TreningsøktID`, `Datotid`, `Varighet`, `Form`) VALUES ('5', '2019-04-19T00:00:00.000', '02:00:00', '1');
INSERT INTO `magnram_db`.`Treningsøkt` (`TreningsøktID`, `Datotid`, `Varighet`, `Form`) VALUES ('6', '2019-04-03T00:00:00.000', '02:00:00', '2');
INSERT INTO `magnram_db`.`Treningsøkt` (`TreningsøktID`, `Datotid`, `Varighet`, `Form`) VALUES ('7', '2019-04-10T00:00:00.000', '02:00:00', '5');
INSERT INTO `magnram_db`.`Treningsøkt` (`TreningsøktID`, `Datotid`, `Varighet`, `Form`) VALUES ('8', '2019-04-03T00:00:00.000', '02:00:00', '3');
INSERT INTO `magnram_db`.`Treningsøkt` (`TreningsøktID`, `Datotid`, `Varighet`, `Form`) VALUES ('9', '2019-04-17T00:00:00.000', '02:00:00', '2');
INSERT INTO `magnram_db`.`Treningsøkt` (`TreningsøktID`, `Datotid`, `Varighet`, `Form`) VALUES ('10', '2019-04-22T00:00:00.000', '02:00:00', '6');

/* FILL GRUPPE */
INSERT INTO `magnram_db`.`Gruppe` (`GruppeID`, `Navn`) VALUES ('1', 'Legg');
INSERT INTO `magnram_db`.`Gruppe` (`GruppeID`, `Navn`) VALUES ('2', 'Lår');
INSERT INTO `magnram_db`.`Gruppe` (`GruppeID`, `Navn`) VALUES ('3', 'Rygg');
INSERT INTO `magnram_db`.`Gruppe` (`GruppeID`, `Navn`) VALUES ('4', 'Abs');
INSERT INTO `magnram_db`.`Gruppe` (`GruppeID`, `Navn`) VALUES ('5', 'Bryst');
INSERT INTO `magnram_db`.`Gruppe` (`GruppeID`, `Navn`) VALUES ('6', 'Biceps');
INSERT INTO `magnram_db`.`Gruppe` (`GruppeID`, `Navn`) VALUES ('7', 'Triceps');
INSERT INTO `magnram_db`.`Gruppe` (`GruppeID`, `Navn`) VALUES ('8', 'Rygg');

/* FILL APPARAT */
INSERT INTO `magnram_db`.`Apparat` (`Navn`, `Beskrivelse`) VALUES ('Weighted arm bands', 'A little weight goes a long way, and Tone-y-Bands are proof. Clocking in at 0.5–1 pound, this colorful wristwear adds a touch more resistance to everyday exercise.');
INSERT INTO `magnram_db`.`Apparat` (`Navn`, `Beskrivelse`) VALUES ('TRX bands', 'When anchored to the wall or a sturdy door, the TRX Home2 System becomes a full-body training system that helps you build muscle while perfecting your posture and alignment. You can var the degree of difficulty by changing your body angle.');
INSERT INTO `magnram_db`.`Apparat` (`Navn`, `Beskrivelse`) VALUES ('Weighted jump rope', 'Did you know one study found that just 10 minutes of jumping rope can be equivalent to a 30-minute jog? Exactly. What’s more, the P90X Adjustable Weighted Jump Rope comes with removable 1-pound weights stashed in the handles.');
INSERT INTO `magnram_db`.`Apparat` (`Navn`, `Beskrivelse`) VALUES ('Medicine ball', 'A simple all-around fitness tool, the 10-pound Spri Xerball Medicine Ball can be lifted, thrown, or slammed to beef up your strength and conditioning.');
INSERT INTO `magnram_db`.`Apparat` (`Navn`, `Beskrivelse`) VALUES ('Dumbells', 'With a quick turn of a dial, you can choose the perfect poundage—from 5 to 52.5—on the Bowflex SelectTech 552 Dumbbells.');
INSERT INTO `magnram_db`.`Apparat` (`Navn`, `Beskrivelse`) VALUES ('Kettleball', 'From single-leg deadlifts to swings, a kettlebell allows for myriad strength moves in limited space. Our fave: the Titan Fitness Adjustable Kettlebell Weight, which converts from 5 to 20 pounds.');
INSERT INTO `magnram_db`.`Apparat` (`Navn`, `Beskrivelse`) VALUES ('Treadmill', 'Cardio your thing? The Weslo Cadence G 5.9i Folding Treadmill is a good bet: It rings in at an affordable price point, and can be folded up, making it perfect for a home gym. Also good? iFit Ready technology, which boasts tracking and trainer workouts.');
INSERT INTO `magnram_db`.`Apparat` (`ApparatID`, `Navn`, `Beskrivelse`) VALUES ('8', 'Horizontal Seated Leg Press', 'All of the trainers we spoke with agreed this was their go-to lower-body machine. ');
INSERT INTO `magnram_db`.`Apparat` (`ApparatID`, `Navn`, `Beskrivelse`) VALUES ('9', 'Lat Pull-Down', 'f you’re interested in ever doing a pull-up, this is a great place to start.');
INSERT INTO `magnram_db`.`Apparat` (`ApparatID`, `Navn`, `Beskrivelse`) VALUES ('10', 'Cable Biceps Bar', 'hese are great for avoiding the swinging that happens with dumbbells');

/* FILL ØVELSE */
INSERT INTO `magnram_db`.`Øvelse` (`ØvelseID`, `Navn`) VALUES ('1', 'Push-ups');
INSERT INTO `magnram_db`.`Øvelse` (`ØvelseID`, `Navn`) VALUES ('2', 'Squats');
INSERT INTO `magnram_db`.`Øvelse` (`ØvelseID`, `Navn`) VALUES ('3', 'Lunges');
INSERT INTO `magnram_db`.`Øvelse` (`ØvelseID`, `Navn`) VALUES ('4', 'Pike Roll-Out');
INSERT INTO `magnram_db`.`Øvelse` (`ØvelseID`, `Navn`) VALUES ('5', 'Clean and Jerk');
INSERT INTO `magnram_db`.`Øvelse` (`ØvelseID`, `Navn`) VALUES ('6', 'Burpees');
INSERT INTO `magnram_db`.`Øvelse` (`ØvelseID`, `Navn`) VALUES ('7', 'Deadlift');
INSERT INTO `magnram_db`.`Øvelse` (`ØvelseID`, `Navn`) VALUES ('8', 'Bench Press');
INSERT INTO `magnram_db`.`Øvelse` (`ØvelseID`, `Navn`) VALUES ('9', 'Goblet Squat');
INSERT INTO `magnram_db`.`Øvelse` (`ØvelseID`, `Navn`) VALUES ('10', 'Farmers\' walk');

/* FILL FRIØVELSE */
INSERT INTO `magnram_db`.`FriØvelse` (`ØvelseID`, `Beskrivelse`) VALUES ('1', 'A push-up (or press-up) is a common calisthenics exercise beginning from the prone position, or the front leaning rest position known in the military.');
INSERT INTO `magnram_db`.`FriØvelse` (`ØvelseID`, `Beskrivelse`) VALUES ('3', 'A lunge can refer to any position of the human body where one leg is positioned forward with knee bent and foot flat on the ground while the other leg is positioned behind.');
INSERT INTO `magnram_db`.`FriØvelse` (`ØvelseID`, `Beskrivelse`) VALUES ('4', 'Researchers found that the Swiss ball rollout and the Swiss ball pike were the most effective in activating the upper and lower abs and external/internal obliques.');
INSERT INTO `magnram_db`.`FriØvelse` (`ØvelseID`, `Beskrivelse`) VALUES ('7', 'The deadlift is a weight training exercise in which a loaded barbell or bar is lifted off the ground to the level of the hips, then lowered to the ground.');
INSERT INTO `magnram_db`.`FriØvelse` (`ØvelseID`, `Beskrivelse`) VALUES ('9', 'The squat is one of the three lifts in the strength sport of powerlifting, together with deadlifts and bench press. It is also considered a staple in many popular recreational exercise programs.');
INSERT INTO `magnram_db`.`FriØvelse` (`ØvelseID`, `Beskrivelse`) VALUES ('10', 'Walk forward taking short, quick steps. Go for the given distance, as fast as possible.');

/* FILL APPARATØVELSE */
INSERT INTO `magnram_db`.`ApparatØvelse` (`ØvelseID`, `AntallKilo`, `AntallSett`, `ApparatID`) VALUES ('2', '34', '3', '5');
INSERT INTO `magnram_db`.`ApparatØvelse` (`ØvelseID`, `AntallKilo`, `AntallSett`, `ApparatID`) VALUES ('5', '23', '5', '4');
INSERT INTO `magnram_db`.`ApparatØvelse` (`ØvelseID`, `AntallKilo`, `AntallSett`, `ApparatID`) VALUES ('6', '14', '2', '2');
INSERT INTO `magnram_db`.`ApparatØvelse` (`ØvelseID`, `AntallKilo`, `AntallSett`, `ApparatID`) VALUES ('8', '25', '1', '5');

/* FILL ØVELSEIGRUPPE */
INSERT INTO `magnram_db`.`ØvelseIGruppe` (`ØvelseID`, `GruppeID`) VALUES ('1', '2');
INSERT INTO `magnram_db`.`ØvelseIGruppe` (`ØvelseID`, `GruppeID`) VALUES ('2', '4');
INSERT INTO `magnram_db`.`ØvelseIGruppe` (`ØvelseID`, `GruppeID`) VALUES ('3', '3');
INSERT INTO `magnram_db`.`ØvelseIGruppe` (`ØvelseID`, `GruppeID`) VALUES ('4', '6');
INSERT INTO `magnram_db`.`ØvelseIGruppe` (`ØvelseID`, `GruppeID`) VALUES ('5', '8');
INSERT INTO `magnram_db`.`ØvelseIGruppe` (`ØvelseID`, `GruppeID`) VALUES ('6', '2');
INSERT INTO `magnram_db`.`ØvelseIGruppe` (`ØvelseID`, `GruppeID`) VALUES ('7', '3');
INSERT INTO `magnram_db`.`ØvelseIGruppe` (`ØvelseID`, `GruppeID`) VALUES ('8', '6');
INSERT INTO `magnram_db`.`ØvelseIGruppe` (`ØvelseID`, `GruppeID`) VALUES ('9', '2');
INSERT INTO `magnram_db`.`ØvelseIGruppe` (`ØvelseID`, `GruppeID`) VALUES ('10', '4');

/* FILL ØVELSEITRENINGSØKT */
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('3', '1', '2');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('5', '1', '4');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('8', '1', '6');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('2', '1', '8');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('7', '2', '2');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('9', '2', '10');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('3', '2', '3');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('2', '2', '9');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('7', '3', '6');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('9', '3', '1');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('4', '3', '10');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('2', '3', '2');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('1', '4', '6');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('7', '4', '8');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('9', '4', '9');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('10', '5', '2');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('2', '5', '6');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('4', '5', '8');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('6', '5', '3');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('7', '5', '5');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('9', '6', '7');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('3', '6', '10');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('10', '6', '2');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('2', '6', '5');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('6', '7', '10');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('8', '7', '2');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('2', '7', '3');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('4', '8', '1');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('6', '8', '2');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('8', '9', '1');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('9', '10', '7');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('4', '10', '4');
INSERT INTO `magnram_db`.`ØvelseITreningsøkt` (`ØvelseID`, `TreningsøktID`, `Prestasjon`) VALUES ('3', '10', '6');
