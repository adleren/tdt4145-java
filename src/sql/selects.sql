/*TreningsøktID, ØvelseID, Datotid, Varighet, Form, Prestasjon, Navn*/
SELECT TreningsøktID, COUNT(*) AS Antall_Øvelser, AVG(Prestasjon) AS Gj_Prestasjon, Datotid, Form, Varighet
FROM (SELECT * FROM Treningsøkt ORDER BY datotid DESC LIMIT 2) AS T
	NATURAL LEFT JOIN ØvelseITreningsøkt 
    NATURAL LEFT JOIN (Øvelse NATURAL LEFT JOIN ApparatØvelse NATURAL LEFT JOIN FriØvelse)
GROUP BY TreningsøktID
ORDER BY datotid DESC;
-- ORDER BY Datotid DESC

SELECT TreningsøktID, ØvelseID, Datotid, Varighet, Form, Prestasjon, Navn
FROM (SELECT * FROM Treningsøkt ORDER BY datotid DESC LIMIT 2) AS T
	NATURAL LEFT JOIN ØvelseITreningsøkt 
    NATURAL LEFT JOIN (Øvelse NATURAL LEFT JOIN ApparatØvelse NATURAL LEFT JOIN FriØvelse)
ORDER BY Datotid DESC;

SELECT *
FROM Øvelse NATURAL JOIN ØvelseITreningsøkt NATURAL JOIN Treningsøkt
WHERE (Datotid BETWEEN '2019-04-05T00:00:00.000' AND '2019-04-15T00:00:00.000')
AND ØvelseID = 2;

UPDATE Treningsøkt SET Datotid = "2020-04-23 18:25:00", Varighet = "02:00:00", Form = 1 WHERE TreningsøktID = 1;