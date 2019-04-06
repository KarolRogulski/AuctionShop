insert into AUCTION (auction_id, title, description, price, auction_beginning, auction_end, user_id)
            values (1, 'someTitle1', 'someDescription1', 200.0,
                    TO_DATE('2012-07-18 13:27:18', 'YYYY-MM-DD HH24:MI:SS'),
                     TO_DATE('2012-08-12 13:53:33', 'YYYY-MM-DD HH24:MI:SS') , 1),

                      (2, 'someTitle2', 'someDescription2', 400.0,
                        TO_DATE('2012-07-19 18:23:56', 'YYYY-MM-DD HH24:MI:SS'),
                         TO_DATE('2012-09-01 18:23:56', 'YYYY-MM-DD HH24:MI:SS'), 2);
