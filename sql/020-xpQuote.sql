use ERPO;

create procedure xpQuote(@dst varchar(16384) output, @src varchar(16384)) as begin
    select @dst=
        (case when @src is null then 'null'
        else '"'+str_replace(str_replace(str_replace(rtrim(@src),'\','\\'),'"','\"'),'
        ','\n')+'"' end)
end;

create procedure xpQuote_proc(@s varchar(16384)) as begin
    declare @dst varchar(16384)
    exec xpQuote @dst out, @s
    select @dst
end;

