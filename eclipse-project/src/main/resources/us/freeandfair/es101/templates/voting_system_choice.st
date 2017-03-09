voting_system_choice(voting_systems) ::= <<
<div align="center">
<table border="1">
<tr><th colspan="2">Choose a Voting System</b></th></tr>
$voting_systems:{ voting_system |
  <tr><td align="center" valign="center">
      <button type="button" name="$voting_system.schema$"
       onclick="window.location.href='$voting_system.schema$'">$voting_system.name$</button></td>
      <td>$voting_system.usageRegions$</td></tr> 
 }$
</table>
</div>
>>