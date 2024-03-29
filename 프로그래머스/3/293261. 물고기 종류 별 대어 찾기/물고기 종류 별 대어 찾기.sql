-- 물고기 종류 별로 가장 큰 물고기의 ID, 물고기 이름, 길이

SELECT ID, FISH_NAME, LENGTH
FROM (
    FISH_INFO LEFT JOIN FISH_NAME_INFO ON FISH_INFO.FISH_TYPE=FISH_NAME_INFO.FISH_TYPE
)
WHERE (FISH_INFO.FISH_TYPE, LENGTH) IN (
    SELECT FISH_TYPE, MAX(LENGTH)
    FROM FISH_INFO
    GROUP BY FISH_TYPE
)