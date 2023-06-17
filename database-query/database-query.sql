SELECT u.user_id, u.username, td.tranining_id, COUNT(*) as lesson_count, td.training_data
FROM user_table u
JOIN training_details td ON u.user_id = td.user_id
GROUP BY u.user_id, u.username, td.tranining_id, DATE(td.training_data)
HAVING COUNT(*) > 1
ORDER BY td.training_data DESC;
