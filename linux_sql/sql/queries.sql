/* Function to round off the timestamp to 5mins*/
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
    $$
BEGIN
RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
LANGUAGE PLPGSQL;

/*Query to group hosts by CPU number and sort by their memory size in descending order(within each cpu_number group)*/

SELECT
    cpu_number,
    id,
    ROUND(total_mem/1024) as total_memory
FROM
    host_info
GROUP BY
    cpu_number,
    id
ORDER BY
    cpu_number,
    total_mem DESC;

/*Query to find average used memory in percentage over 5 mins interval for each host. */
SELECT
    host_id,
    hostname,
    cast(AVG((
                     (
                             ROUND(hi.total_mem / 1024)- ho.memory_free
                         )/(
                         ROUND(hi.total_mem / 1024)
                         )
                 ) * 100) as INT) as avg_used_mem,
    round5(ho.timestamp)
FROM
    host_usage ho
        JOIN host_info hi ON hi.id = ho.host_id
GROUP BY
    ho.timestamp,
    host_id,
    hostname,
    total_mem;

/*Query to detect host failure "Crontab is supposed to input data every 5 min */

SELECT
    DISTINCT host_id,
             round5(host_usage.timestamp) as Five_min_stamps,
             COUNT(*) as num_data_points
FROM
    host_usage
GROUP BY
    Five_min_stamps,
    host_id
HAVING
        count(*) <= 3;








