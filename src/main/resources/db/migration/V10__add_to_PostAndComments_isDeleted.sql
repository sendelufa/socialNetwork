ALTER TABLE post ADD is_deleted BIT NOT NULL DEFAULT 0;
ALTER TABLE post_comment ADD is_deleted BIT NOT NULL DEFAULT 0;