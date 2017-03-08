voting_system_choice(voting_systems) ::= <<
<div align="center">
<table border="1">
<tr><th colspan="2">Choose a Voting System</b></th></tr>
$voting_systems:{ voting_system |
  <tr><td valign="center"><a href="$voting_system.schema$">$voting_system.name$</a></td>
      <td>$voting_system.usageRegions$</td></tr> 
 }$
</table>
</div>
>>