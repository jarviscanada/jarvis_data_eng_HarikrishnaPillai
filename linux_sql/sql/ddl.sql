/* Table to create hardware specifications */
CREATE TABLE PUBLIC.host_info (
                                  id SERIAL NOT NULL,
                                  hostname VARCHAR NOT NULL UNIQUE,
                                  cpu_number INT NOT NULL,
                                  cpu_architecture VARCHAR NOT NULL,
                                  cpu_model VARCHAR NOT NULL,
                                  cpu_mhz DECIMAL(7, 3) NOT NULL,
                                  L2_cache VARCHAR NOT NULL,
                                  total_mem INT NOT NULL,
                                  "timestamp" TIMESTAMP NOT NULL,
                                  PRIMARY KEY (id)
);
/* Table to create usage data */
CREATE TABLE PUBLIC.host_usage (
                                   "timestamp" TIMESTAMP NOT NULL,
                                   host_id SERIAL NOT NULL,
                                   memory_free INT NOT NULL,
                                   cpu_idle INT NOT NULL,
                                   cpu_kernel INT NOT NULL,
                                   disk_io INT NOT NULL,
                                   disk_available VARCHAR NOT NULL,
                                   FOREIGN KEY (host_id) REFERENCES host_info(id)
);